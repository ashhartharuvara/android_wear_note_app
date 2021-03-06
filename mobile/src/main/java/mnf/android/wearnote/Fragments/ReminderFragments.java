package mnf.android.wearnote.Fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mnf.android.wearnote.Adapters.RecycleViewAdapter;
import mnf.android.wearnote.Adapters.RecycleViewReminderAdapter;
import mnf.android.wearnote.Config;
import mnf.android.wearnote.Model.ReminderModel;
import mnf.android.wearnote.R;
import mnf.android.wearnote.tools.MobilePreferenceHandler;
import mnf.android.wearnote.tools.SimpleDividerItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReminderFragments.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReminderFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReminderFragments extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView emptyPlaceholderRm;

    private OnFragmentInteractionListener mListener;

 //   @BindView(R.id.rc_reminder)
    RecyclerView recyclerView;
    private RecycleViewReminderAdapter adapter;

    public ReminderFragments() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReminderFragments.
     */
    // TODO: Rename and change types and number of parameters
    public static ReminderFragments newInstance(String param1, String param2) {
        ReminderFragments fragment = new ReminderFragments();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    MobilePreferenceHandler pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reminder_fragments, container, false);
        //ButterKnife.bind(getActivity());
        getActivity().setTitle("Reminders");
        recyclerView = (RecyclerView) v.findViewById(R.id.rc_reminder);
        emptyPlaceholderRm = (TextView) v.findViewById(R.id.empty_placeholder_rm);
        Typeface face=Typeface.createFromAsset(getActivity().getAssets(), "fonts/Cabin-Regular.ttf");
        emptyPlaceholderRm.setTypeface(face);

        List<ReminderModel> listItems = Config.getReminderList();
        if(listItems.size()<=0){
            emptyPlaceholderRm.setVisibility(View.VISIBLE);
        }
        adapter = new RecycleViewReminderAdapter(getActivity(), listItems);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        //  recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, Config.dpToPx(1,getApplicationContext()), true));
        //recyclerView.addItemDecoration(new MarginDecoration(this));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
