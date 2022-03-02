package de.hshl.isd.mensa.ui.main;

import androidx.appcompat.app.AlertDialog;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hshl.isd.mensa.MealQueryDTO;
import de.hshl.isd.mensa.R;
import io.github.italbytz.adapters.meal.OpenMensaGetMealsCommand;
import io.github.italbytz.ports.meal.GetMealsCommand;
import io.github.italbytz.ports.meal.Meal;
import io.github.italbytz.ports.meal.MealCollection;
import static de.hshl.isd.mensa.CoroutineWrapperKt.*;

public class MainFragment extends Fragment {

    private GetMealsCommand command = new OpenMensaGetMealsCommand();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(requireActivity());
        int status = Integer.parseInt(
                sharedPreferences.getString("PREF_STATUS","0"));

        MainListAdapter adapter = new MainListAdapter();
        adapter.submitList(new ArrayList<ItemViewModel>());
        RecyclerView recyclerView = requireActivity().findViewById(R.id.list);
        recyclerView.setAdapter(adapter);

        try {
            executeFromJava(command,new
                    MealQueryDTO(42, LocalDate.now())).thenAccept(meals -> {


                List<ItemViewModel> mealList = new ArrayList<ItemViewModel>();
                for (MealCollection mealCollection:meals) {
                    String category = "";
                    switch (mealCollection.getCategory()) {
                        case NONE:
                            category = "";
                            break;
                        case DESSERT:
                            category = "Dessert";
                            break;
                        case DISH:
                            category = "Hauptgerichte";
                            break;
                        case SIDEDISH:
                            category = "Beilagen";
                            break;
                        case SOUP:
                            category = "Suppen / Eintöpfe";
                            break;
                    }
                    mealList.add(new ItemViewModel(category));
                    for (Meal meal:mealCollection.getMeals()) {
                        double price = 0.0;
                        switch (status) {
                            case 0: price = meal.getPrice().getStudents(); break;
                            case 1: price = meal.getPrice().getEmployees(); break;
                            case 2: price = meal.getPrice().getPupils(); break;
                            case 3: price = meal.getPrice().getOthers(); break;
                        }
                        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
                        mealList.add(new ImageItemViewModel(meal.getName(), meal.getImage(), formatter.format(price)));
                    }
                }
                adapter.submitList(mealList);
            });

        } catch (Exception ex) {
            (new AlertDialog.Builder(requireActivity()))
                .setMessage(ex.getLocalizedMessage())
                .setTitle(android.R.string.dialog_alert_title)
                .setPositiveButton(android.R.string.ok, null)
                    .create()
                    .show();
        }

    }

}