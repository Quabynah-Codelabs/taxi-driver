package com.innomalist.taxi.common.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.innomalist.taxi.common.BR;
import com.innomalist.taxi.common.custom.BaseUser;

import java.io.Serializable;
import java.sql.Timestamp;

public class Driver extends BaseObservable implements Serializable, BaseUser {
    @SerializedName("registration_timestamp")
    private Timestamp registrationTimestamp;

    @SerializedName("account_number")
    private String accountNumber;

    @SerializedName("media")
    private Media media;

    @SerializedName("car_media")
    private Media carMedia;

    @Expose
    @SerializedName("car_plate")
    private String carPlate;

    @Expose
    @SerializedName("address")
    private String address;

    @Expose
    @SerializedName("gender")
    private Gender gender;

    @SerializedName("rating")
    private Integer rating;

    @SerializedName("info_changed")
    private int infoChanged;

    @Expose
    @SerializedName("last_name")
    private String lastName;

    @SerializedName("review_count")
    private int reviewCount;

    @Expose
    @SerializedName("car_color")
    private String carColor;

    @SerializedName("certificate_number")
    private String certificateNumber;

    @SerializedName("password")
    private String password;

    @SerializedName("balance")
    private Double balance;

    @SerializedName("car_production_year")
    private Integer carProductionYear;

    @SerializedName("id")
    private int id;

    @SerializedName("mobile_number")
    private long mobileNumber;

    @Expose
    @SerializedName("first_name")
    private String firstName;

    @SerializedName("car")
    private Car car;

    @Expose
    @SerializedName("email")
    private String email;

    @SerializedName("status")
    private String status;

    @Expose
    public String uid;

    public Driver() {
    }

    public Driver(String uid, Timestamp registrationTimestamp, String accountNumber, Media media,
                  Media carMedia, String carPlate, String address, Gender gender,
                  Integer rating, int infoChanged, String lastName, int reviewCount,
                  String carColor, String certificateNumber, String password, Double balance,
                  Integer carProductionYear,
                  int id, long mobileNumber, String firstName, Car car, String email, String status) {
        this.uid = uid;
        this.registrationTimestamp = registrationTimestamp;
        this.accountNumber = accountNumber;
        this.media = media;
        this.carMedia = carMedia;
        this.carPlate = carPlate;
        this.address = address;
        this.gender = gender;
        this.rating = rating;
        this.infoChanged = infoChanged;
        this.lastName = lastName;
        this.reviewCount = reviewCount;
        this.carColor = carColor;
        this.certificateNumber = certificateNumber;
        this.password = password;
        this.balance = balance;
        this.carProductionYear = carProductionYear;
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.firstName = firstName;
        this.car = car;
        this.email = email;
        this.status = status;
    }

    public Timestamp getRegistrationTimestamp() {
        return registrationTimestamp;
    }

    public void setRegistrationTimestamp(Timestamp registrationTimestamp) {
        this.registrationTimestamp = registrationTimestamp;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Bindable
    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
        // todo bind media
        notifyPropertyChanged(BR._all);
    }

    @Bindable
    public Media getCarMedia() {
        return carMedia;
    }

    public void setCarMedia(Media carMedia) {
        this.carMedia = carMedia;
        // todo bind carMedia
        notifyPropertyChanged(BR._all);
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Bindable
    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
        notifyPropertyChanged(com.innomalist.taxi.common.BR.gender);
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public boolean isInfoChanged() {
        return infoChanged == 1;
    }

    public void setInfoChanged(boolean infoChanged) {
        this.infoChanged = infoChanged ? 1 : 0;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getCarProductionYear() {
        return carProductionYear;
    }

    public void setCarProductionYear(Integer carProductionYear) {
        this.carProductionYear = carProductionYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toJson() {
        GsonBuilder b = new GsonBuilder();
        Gson gson = b.excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this);
    }

    public static class Builder {
        Timestamp registrationTimestamp;
        Media media, carMedia;
        Double balance;
        Gender gender;
        Car car;
        String uid, accountNumber, carPlate, address, lastName, carColor, certificateNumber,
                password, firstName, status, email;
        Integer rating, carProductionYear;
        int infoChanged, reviewCount, id;
        long mobileNumber;

        public Builder setUID(String uid) {
            this.uid = uid;
            return this;
        }

        public Builder setRegistrationTimestamp(Timestamp registrationTimestamp) {
            this.registrationTimestamp = registrationTimestamp;
            return this;
        }

        public Builder setMedia(Media media) {
            this.media = media;
            return this;
        }

        public Builder setCarMedia(Media carMedia) {
            this.carMedia = carMedia;
            return this;
        }

        public Builder setBalance(Double balance) {
            this.balance = balance;
            return this;
        }

        public Builder setGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder setCar(Car car) {
            this.car = car;
            return this;
        }

        public Builder setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder setCarPlate(String carPlate) {
            this.carPlate = carPlate;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setCarColor(String carColor) {
            this.carColor = carColor;
            return this;
        }

        public Builder setCertificateNumber(String certificateNumber) {
            this.certificateNumber = certificateNumber;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
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

        public Builder setRating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Builder setCarProductionYear(Integer carProductionYear) {
            this.carProductionYear = carProductionYear;
            return this;
        }

        public Builder setInfoChanged(int infoChanged) {
            this.infoChanged = infoChanged;
            return this;
        }

        public Builder setReviewCount(int reviewCount) {
            this.reviewCount = reviewCount;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setMobileNumber(long mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public Driver build() {
            return new Driver(uid, registrationTimestamp, accountNumber, media, carMedia, carPlate, address,
                    Gender.male, rating, infoChanged, lastName, reviewCount, carColor,
                    certificateNumber, password, balance, carProductionYear, id, mobileNumber, firstName, car,
                    email, status);
        }
    }
}