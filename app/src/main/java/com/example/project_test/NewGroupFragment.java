package com.example.project_test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class NewGroupFragment extends Fragment {

    private View view;

    private Button button;
    private EditText groupName;
    private EditText className;
    private Button createGroupbutton;

    private FirestoreHandler firestoreHandler= new FirestoreHandler();

    public NewGroupFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_group, container, false);

        button = view.findViewById(R.id.backButton);
        groupName = view.findViewById(R.id.groupNameInput);
        className = view.findViewById(R.id.classInput);

        createGroupbutton = view.findViewById(R.id.createGroupButton);




        createGroupbutton.setOnClickListener(v -> {
                System.out.println("uspjeh");
                String groupNameString = groupName.getText().toString().trim();
                String classNameString = className.getText().toString().trim();
                StudyGroup studyGroup = new StudyGroup(groupNameString,className.getText().toString().trim(),null);
                firestoreHandler.createNewStudyGroup(studyGroup);

        });


        return view;


    }

}
