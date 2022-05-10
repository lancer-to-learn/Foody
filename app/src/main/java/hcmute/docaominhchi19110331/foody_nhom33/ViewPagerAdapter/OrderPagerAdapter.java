package hcmute.docaominhchi19110331.foody_nhom33.ViewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import hcmute.docaominhchi19110331.foody_nhom33.CustomerInfoFragment;
import hcmute.docaominhchi19110331.foody_nhom33.Food;
import hcmute.docaominhchi19110331.foody_nhom33.FoodInfoFragment;


public class OrderPagerAdapter extends FragmentStatePagerAdapter {
    Food getFood;
    public OrderPagerAdapter(@NonNull FragmentManager fm, int behavior, Food thisFood) {
        super(fm, behavior);
        getFood = thisFood;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new FoodInfoFragment(getFood);

            case 1: return new CustomerInfoFragment();

            default: return new FoodInfoFragment(getFood);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Food";
                break;

            case 1:
                title = "Customer";
                break;

        }
        return title;
    }
}
