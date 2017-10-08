package potential_couscous.couscousdrive.controllers;

import android.support.v4.app.Fragment;

/**
 * Interface for telling MainActivity to replace Fragments.
 */
public interface IFragmentChanger {
    void replaceFragment(Fragment fragment);
}