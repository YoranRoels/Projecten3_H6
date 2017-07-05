package projecten3_h6.evaandroid.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import projecten3_h6.evaandroid.Domain.Ingredient;
import projecten3_h6.evaandroid.R;

/**
 * Created by Mafken on 4/07/2017.
 */

public class ShoppinglistFragment extends Fragment {

    @BindView(R.id.shoppingListRecyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.addShoppingListItemtxt) TextView addShoppingListItemtxt;

    protected RecyclerView.LayoutManager mLayoutManager;
    ShoppinglistAdapter adapter ;
    ArrayList<Ingredient> shoppinglist = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v = inflater.inflate(R.layout.fragment_shoppinglist, container, false);
        ButterKnife.bind(this,v);
        initdata();

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(mRecyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipeLeft(int position) {
                                return true;
                            }

                            @Override
                            public boolean canSwipeRight(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    Toast.makeText(getActivity(), shoppinglist.get(position).getName() + " has been deleted from the list", Toast.LENGTH_SHORT).show();
                                    shoppinglist.remove(position);
                                }
                                initCard();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    Toast.makeText(getActivity(), shoppinglist.get(position).getName() + " has been deleted from the list", Toast.LENGTH_SHORT).show();
                                    shoppinglist.remove(position);
                                }
                                initCard();
                            }
                        });

        mRecyclerView.addOnItemTouchListener(swipeTouchListener);



        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        initCard();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Shopping List");
    }

    @OnClick(R.id.addShoppingListItembtn)
    public void addShoppingListItem(){
        shoppinglist.add(new Ingredient(addShoppingListItemtxt.getText().toString(),"")); //als je het aantal ook apart wil laten tonen hier inserten dan

        // view verversen voor nieuw item te tonen
        initCard();

        Toast.makeText(getActivity(), addShoppingListItemtxt.getText().toString() + " has been added to the list", Toast.LENGTH_LONG).show();
        addShoppingListItemtxt.setText("");
    }

    public void initCard(){
        adapter = new ShoppinglistAdapter(shoppinglist);
        mRecyclerView.setAdapter(adapter);
    }

    public void initdata(){
        shoppinglist.add(new Ingredient("Wortelen","20"));
        shoppinglist.add(new Ingredient("Tomaten","15"));
        shoppinglist.add(new Ingredient("Eiren","1"));
        shoppinglist.add(new Ingredient("vanille","200g"));
        shoppinglist.add(new Ingredient("Basilicum","1000g"));
        shoppinglist.add(new Ingredient("Wortelen","20"));
        shoppinglist.add(new Ingredient("Wortelen","20"));
        shoppinglist.add(new Ingredient("Tomaten","15"));
        shoppinglist.add(new Ingredient("Eiren","1"));
        shoppinglist.add(new Ingredient("vanille","200g"));
        shoppinglist.add(new Ingredient("Basilicum","1000g"));
        shoppinglist.add(new Ingredient("Wortelen","20"));
        shoppinglist.add(new Ingredient("Wortelen","20"));
        shoppinglist.add(new Ingredient("Tomaten","15"));
        shoppinglist.add(new Ingredient("Eiren","1"));
        shoppinglist.add(new Ingredient("vanille","200g"));
        shoppinglist.add(new Ingredient("Basilicum","1000g"));
        shoppinglist.add(new Ingredient("Wortelen","20"));
        shoppinglist.add(new Ingredient("Wortelen","20"));
        shoppinglist.add(new Ingredient("Tomaten","15"));
        shoppinglist.add(new Ingredient("Eiren","1"));
        shoppinglist.add(new Ingredient("vanille","200g"));
        shoppinglist.add(new Ingredient("Basilicum","1000g"));
        shoppinglist.add(new Ingredient("Wortelen","20"));
    }
}
