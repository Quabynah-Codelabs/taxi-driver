package com.innomalist.taxi.common.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.innomalist.taxi.common.BR;
import com.innomalist.taxi.common.custom.BaseUser;

public class Rider extends BaseObservable implements BaseUser {

    public String uid;
    public long id;
    @Expose
    @SerializedName("first_name")
    public String firstName;

    @Expose
    @SerializedName("last_name")
    public String lastName;

    public Media media;

    @Expose
    @SerializedName("mobile_number")
    public long mobileNumber;

    public String status;

    @Expose
    public String email;

    @Expose
    public Gender gender;

    @Expose
    @SerializedName("balance")
    private Double balance;

    @Expose
    public String address;

    public static Rider fromJson(String json) {
        return (new GsonBuilder()).create().fromJson(json, Rider.class);
    }

    public static String toJson(Rider rider) {
        return (new GsonBuilder().excludeFieldsWithoutExposeAnnotation()).create().toJson(rider);
    }

    public Rider() {
    }

    public Rider(String uid, long id, String firstName, String lastName, Media media, long mobileNumber, String status, String email, Gender gender, Double balance, String address) {
        this.uid = uid;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.media = media;
        this.mobileNumber = mobileNumber;
        this.status = status;
        this.email = email;
        this.gender = gender;
        this.balance = balance;
        this.address = address;
    }

    public static class Builder {
        long id, mobileNumber;
        Gender gender;
        String uid, firstName, lastName, status, email, address;
        Double balance;
        Media media;

        public Builder setUID(String uid) {
            this.uid = uid;
            return this;
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setMobileNumber(long mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public Builder setGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setBalance(Double balance) {
            this.balance = balance;
            return this;
        }

        public Builder setMedia(Media media) {
            this.media = media;
            return this;
        }

        public Rider build() {
            return new Rider(this.uid, this.id, this.firstName, this.lastName, this.media, this.mobileNumber,
                    this.status, this.email, this.gender, this.balance, this.address);
        }
    }

    @Bindable
    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Bindable
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(com.innomalist.taxi.common.BR.firstName);
    }

    @Bindable
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(com.innomalist.taxi.common.BR.lastName);
    }

    @Bindable
    public long getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
        notifyPropertyChanged(com.innomalist.taxi.common.BR.mobileNumber);
    }

    @Bindable
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(com.innomalist.taxi.common.BR.address);
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Bindable
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(com.innomalist.taxi.common.BR.email);
    }

    @Bindable
    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
        // todo : update media
        notifyPropertyChanged(BR._all);
    }

    public Double getBalance() {
        return balance;
    }
}
