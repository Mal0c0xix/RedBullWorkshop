package comind.fr.redbullworks.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import comind.fr.redbullworks.fragment.tablayoutsFragment.CurrentsCommandFragment;
import comind.fr.redbullworks.fragment.tablayoutsFragment.CurrentsCommandFragment_;
import comind.fr.redbullworks.fragment.tablayoutsFragment.ReceivedCommandsFragment;
import comind.fr.redbullworks.fragment.tablayoutsFragment.ReceivedCommandsFragment_;

/**
 * Created by Pascal on 22/03/2017.
 *
 */
public class CustomPager extends FragmentStatePagerAdapter {

    public CustomPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CurrentsCommandFragment currentsTab = new CurrentsCommandFragment_();
                return currentsTab;
            case 1:
                ReceivedCommandsFragment receivedTab = new ReceivedCommandsFragment_();
                return receivedTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String result = "En cours";

        if(position == 1) result = "Reçues";

        return result;
    }
}
