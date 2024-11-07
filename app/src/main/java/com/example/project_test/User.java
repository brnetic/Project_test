package com.example.project_test;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class User implements Parcelable {

    public String name;
    private boolean loggedIn;

    private String id;

    public String email;
    public ArrayList<Course> selectedCourses;

    User(){

    }
    User(String name, String id, String email, ArrayList<Course> selectedCourses){
        this.name = name;
        this.email = email;
        this.id = id;
        this.selectedCourses = selectedCourses;
    }
    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(email);
    }



    public boolean isLoggedIn(){
        return loggedIn;
    }


    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public void addCourse(Course course){
        selectedCourses.add(course);
    }
    public void joinStudyGroup(StudyGroup studyGroup){
        studyGroup.addMember(this);
    }


    public void scheduleStudySession(){

    }
    public void uploadResource(){

    }


}
