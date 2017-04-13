package comind.fr.redbullworks.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import comind.fr.redbullworks.R;
import comind.fr.redbullworks.utils.CustomPager;

/**
 * Created by Pascal on 22/03/2017.
 *
 */

@EFragment(R.layout.fragment_commande)
public class CommandeFragment extends Fragment implements TabLayout.OnTabSelectedListener{

    //This is our tabLayout
    @ViewById(R.id.tabLayout)
    TabLayout tabLayout;

    //This is our viewPager
    @ViewById(R.id.pager)
    ViewPager viewPager;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    CustomPager adapter;

    @AfterViews
    void initialize()
    {
        /*
        TabTextColor sets the color for the title of the tabs, passing a ColorStateList here makes
        tab change colors in different situations such as selected, active, inactive etc

        TabIndicatorColor sets the color for the indicator below the tabs
         */
        tabLayout.setTabTextColors(ContextCompat.getColorStateList(getContext(), R.color.tab_selector));

        //Creating our pager adapter
        adapter = new CustomPager(getActivity().getSupportFragmentManager());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);

        //Adding onTabSelectedListener to swipe views
        tabLayout.addOnTabSelectedListener(this);
    }

    //Make the viewpager swap between tabs
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
