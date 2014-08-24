package com.plantwire.mycv;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class EmailFragment extends Fragment {

    private EmailInterface emailInterface;

    private Button mSendButton;

    public EmailFragment() {
    }

    public interface EmailInterface
    {
        public void onEmailSend();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email, container, false);
        mSendButton = (Button)view.findViewById(R.id.emailSendButton);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailInterface.onEmailSend();
            }
        });
        return view;
    }

    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        emailInterface = (EmailInterface) activity;
    }


}
