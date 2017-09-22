package com.example.android.moodindigo.data;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mrunz on 17/9/17.
 */

public class EventDetailResponse {

    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("rules")
    private @Nullable String rules;
    @SerializedName("prizes")
    private @Nullable String prizes;
    @SerializedName("subtitle")
    private @Nullable String subtitle;
    @SerializedName("id")
    private @Nullable int id;
    @SerializedName("maxparticipants")
    private @Nullable int maxparticipants;
    @SerializedName("minparticipants")
    private @Nullable int minparticipants;

    public EventDetailResponse(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public  @Nullable String getRules() {
        return rules;
    }

    public void setRules( @Nullable String rules) {
        this.rules = rules;
    }

    public @Nullable String getPrizes() {
        return prizes;
    }

    public void setPrizes( @Nullable String prizes) {
        this.prizes = prizes;
    }

    public @Nullable String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle( @Nullable String subtitle) {
        this.subtitle = subtitle;
    }

    public @Nullable int getId() {
        return id;
    }

    public void setId( @Nullable int id) {
        this.id = id;
    }

    public @Nullable int getMaxparticipants() {
        return maxparticipants;
    }

    public void setMaxparticipants( @Nullable int maxparticipants) {
        this.maxparticipants = maxparticipants;
    }

    public @Nullable int getMinparticipants() {
        return minparticipants;
    }

    public void setMinparticipants( @Nullable int minparticipants) {
        this.minparticipants = minparticipants;
    }
}
