package in.tvac.akshaye.lapitchat.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.tvac.akshaye.lapitchat.Fragments.ChatsFragment;
import in.tvac.akshaye.lapitchat.Fragments.FriendsFragment;
import in.tvac.akshaye.lapitchat.Fragments.RequestsFragment;
import in.tvac.akshaye.lapitchat.Fragments.UserFragment;

/**
 * Created by AkshayeJH on 11/06/17.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter{


    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch(position) {
            case 0:
                RequestsFragment requestsFragment = new RequestsFragment();
                return requestsFragment;

            case 1:
                ChatsFragment chatsFragment = new ChatsFragment();
                return  chatsFragment;

            case 2:
                FriendsFragment friendsFragment = new FriendsFragment();
                return friendsFragment;
            case 3:
                UserFragment usersFragment = new UserFragment();
                return usersFragment;

            default:
                return  null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    public CharSequence getPageTitle(int position){

        switch (position) {
            case 0:
                return "REQUESTS";

            case 1:
                return "CHATS";

            case 2:
                return "FRIENDS";
            case 3:
                return "USERS";

            default:
                return null;
        }

    }

}
