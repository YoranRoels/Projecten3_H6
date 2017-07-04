package projecten3_h6.evaandroid.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.R;

/**
 * Created by Mafken on 4/07/2017.
 */

public class Shoppinglist extends Fragment {

    @BindView(R.id.shoppingListRecyclerView) RecyclerView mRcyclerView;

    protected RecyclerView.LayoutManager mLayoutManager;
    ShoppinglistAdapter adapter ;
    ArrayList<String> shoppinglist = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v = inflater.inflate(R.layout.fragment_shoppinglist, container, false);
        ButterKnife.bind(this,v);

        shoppinglist.add("Wortelen");
        shoppinglist.add("Wortelen");
        shoppinglist.add("Wortelen");
        shoppinglist.add("Wortelen");
        shoppinglist.add("Wortelen");
        shoppinglist.add("Tomaten");

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRcyclerView.setLayoutManager(mLayoutManager);

        adapter = new ShoppinglistAdapter(shoppinglist);
        mRcyclerView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Shopping List");
    }
}
