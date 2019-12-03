package com.example.casinochipcountingapp;

import android.net.Uri;
import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.zzy;
import com.google.firebase.auth.zzz;

import java.util.List;

public class User extends FirebaseUser {
    private String password;
    private String email;
    private String name;
    private String phoneNumber;
    private boolean isVerified;
    public User(String email, String password, String name,
                String phoneNumber, boolean isVerified) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isVerified = isVerified;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void isVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }
    @NonNull
    @Override
    public String getUid() {
        return null;
    }

    @NonNull
    @Override
    public String getProviderId() {
        return null;
    }

    @Override
    public boolean isAnonymous() {
        return false;
    }

    @Nullable
    @Override
    public List<String> zza() {
        return null;
    }

    @NonNull
    @Override
    public List<? extends UserInfo> getProviderData() {
        return null;
    }

    @NonNull
    @Override
    public FirebaseUser zza(@NonNull List<? extends UserInfo> list) {
        return null;
    }

    @Override
    public FirebaseUser zzb() {
        return null;
    }

    @NonNull
    @Override
    public FirebaseApp zzc() {
        return null;
    }

    @Nullable
    @Override
    public String getDisplayName() {
        return name;
    }

    @Nullable
    @Override
    public Uri getPhotoUrl() {
        return null;
    }

    @Nullable
    @Override
    public String getEmail() {
        return email;
    }

    @Nullable
    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean isEmailVerified() {
        return isVerified;
    }
    public String getPassword() {
        return password;
    }

    @Nullable
    @Override
    public String zzd() {
        return null;
    }

    @NonNull
    @Override
    public zzff zze() {
        return null;
    }

    @Override
    public void zza(@NonNull zzff zzff) {

    }

    @NonNull
    @Override
    public String zzf() {
        return null;
    }

    @NonNull
    @Override
    public String zzg() {
        return null;
    }

    @Nullable
    @Override
    public FirebaseUserMetadata getMetadata() {
        return null;
    }

    @NonNull
    @Override
    public zzz zzh() {
        return null;
    }

    @Override
    public void zzb(List<zzy> list) {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
