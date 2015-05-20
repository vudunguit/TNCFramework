package vn.tnc.core.base.navigator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import java.util.Hashtable;
import java.util.Map;
import java.util.Stack;

import vn.tnc.core.R;

/**
 * Created by TALE on 7/23/2014.
 */
public class FragmentNavigator {

    private final int fragmentContainerId;
    private final FragmentManager fragmentManager;
    private Stack<Fragment> backStack;
    private Map<String, int[]> backAnimation;
    private int curPos;

    private FragmentNavigator(final FragmentActivity activity, int fragmentContainerId) {
        this.fragmentContainerId = fragmentContainerId;
        this.fragmentManager = activity.getSupportFragmentManager();
    }

    public static FragmentNavigator create(FragmentActivity activity, int fragmentContainerId) {
        return new FragmentNavigator(activity, fragmentContainerId);
    }

    public void clearStack() {
        if (backStack != null) {
            backStack.clear();
            backStack = null;
        }
    }

    public void showScreen(Fragment fragment, boolean addToBackStack) {
        showScreen(fragment, addToBackStack, R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void showScreen(Fragment fragment, boolean addToBackStack, int index) {
        if (index > curPos) {
            showScreen(fragment, addToBackStack);
        } else {
            showScreen(fragment, addToBackStack, R.anim.slide_in_right, R.anim.slide_out_left);
        }
        curPos = index;
    }

    /**
     * Change main fragment screen to target screen
     */
    public void showScreen(Fragment fragment, boolean addToBackStack, int animEnter, int animExit) {
        if (addToBackStack) {
            final Fragment curFrag = fragmentManager.findFragmentById(fragmentContainerId);
            if (curFrag != null) {
                if (backStack == null) {
                    backStack = new Stack<>();
                }
                backStack.push(curFrag);
            }
        }

        final String tag = "FRAG_" + fragment.hashCode();
        if (fragmentManager.findFragmentByTag(tag) == null) {
            final FragmentTransaction ft = fragmentManager.beginTransaction();
            if (fragmentManager.findFragmentById(fragmentContainerId) == null) {
                ft.add(fragmentContainerId, fragment, tag);
            } else {
                ft.setCustomAnimations(animEnter, animExit);
                ft.replace(fragmentContainerId, fragment, tag);
            }
            ft.commit();
        }
    }

    /**
     * Change main fragment screen to target screen
     */
    public void showScreen(Fragment fragment, int animEnter, int animExit, int backAnimEnter, int backAnimExit) {
        if (backAnimation == null) {
            backAnimation = new Hashtable<>();
        }

        backAnimation.put(fragment.getClass().getName(), new int[]{backAnimEnter, backAnimExit});
        showScreen(fragment, true, animEnter, animExit);
    }

    public void pop() {
        backStack.pop();
    }

    public boolean navigateBack() {
        if (backStack == null || backStack.isEmpty()) {
            return false;
        }
        final Fragment fragment = backStack.pop();
        final String tag = "FRAG_" + fragment.hashCode();
        final Fragment curFrag = fragmentManager.findFragmentById(fragmentContainerId);
        final int[] backAnim = backAnimation != null ? backAnimation.get(curFrag.getClass().getName()) : null;
        if (fragmentManager.findFragmentByTag(tag) == null) {
            final FragmentTransaction ft = fragmentManager.beginTransaction();
            if (backAnim != null && backAnim.length > 1) {
                ft.setCustomAnimations(backAnim[0], backAnim[1]);
            } else {
                ft.setCustomAnimations(R.anim.slide_in_left,
                        R.anim.slide_out_right);
            }
            ft.replace(fragmentContainerId, fragment, tag);
            ft.commit();
        }
        return true;
    }

}