package de.hshl.isd.mensa.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import de.hshl.isd.mensa.MealQueryDTO;
import de.hshl.isd.mensa.R;
import io.github.italbytz.adapters.meal.MockGetMealsCommand;
import io.github.italbytz.ports.meal.GetMealsCommand;
import io.github.italbytz.ports.meal.MealCollection;
import static de.hshl.isd.mensa.CoroutineWrapperKt.*;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private GetMealsCommand command = new MockGetMealsCommand();

    public static MainFragment newInstance() {
        return new MainFragment();
    }

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

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        RecyclerView recyclerView = requireActivity().findViewById(R.id.list);
        MainListAdapter adapter = new MainListAdapter();

        try {
            List<MealCollection> meals = executeFromJava(command,new
                    MealQueryDTO(42, LocalDate.now())).get();
            Log.i("MainFragment", meals.toString());
            List<ItemViewModel> mealList = new ArrayList<ItemViewModel>();
            mealList.add(new ItemViewModel("Hello"));
            adapter.submitList(mealList);
            recyclerView.setAdapter(adapter);
        } catch (Exception ex) {
            Log.e("MainFragment", ex.getLocalizedMessage());
        }




    }

}