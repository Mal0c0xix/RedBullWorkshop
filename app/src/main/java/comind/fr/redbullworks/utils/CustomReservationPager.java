package comind.fr.redbullworks.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import comind.fr.redbullworks.fragment.tablayoutsFragment.AcceptedReservationFragment_;
import comind.fr.redbullworks.fragment.tablayoutsFragment.CurrentsReservationFragment_;


/**
 * Created by Pascal on 22/03/2017.
 *
 */

public class CustomReservationPager extends FragmentStatePagerAdapter {

    public CustomReservationPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CurrentsReservationFragment_ acceptedTab = new CurrentsReservationFragment_();
                return acceptedTab;
            case 1:
                AcceptedReservationFragment_ currentsTab = new AcceptedReservationFragment_();
                return currentsTab;
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
        String result = "Acceptées";

        if(position == 1) result = "En cours";

        return result;
    }
}
