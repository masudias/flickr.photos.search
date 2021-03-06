package com.masudias.flickerdashboard.domain.db;

import android.database.Cursor;

import com.masudias.flickerdashboard.database.DBConstants;

import java.util.ArrayList;

public class Photo {
    public String photoId;
    public String photoUrl;
    public String owner;
    public String ownerPhotoUrl;
    public String title;
    public Integer height;
    public Integer width;
    public Integer photoSource;

    public boolean isValid() {
        return photoId != null
                && photoUrl != null
                && owner != null
                && ownerPhotoUrl != null
                && title != null
                && height != null
                && width != null
                && photoSource != null;
    }

    private Photo(Builder builder) {
        photoId = builder.photoId;
        photoUrl = builder.photoUrl;
        owner = builder.owner;
        ownerPhotoUrl = builder.ownerPhotoUrl;
        title = builder.title;
        height = builder.height;
        width = builder.width;
        photoSource = builder.photoSource;
    }

    public static final class Builder {
        private String photoId;
        private String photoUrl;
        private String owner;
        private String ownerPhotoUrl;
        private String title;
        private Integer height;
        private Integer width;
        private Integer photoSource;

        public Builder() {
        }

        public Builder photoId(String val) {
            photoId = val;
            return this;
        }

        public Builder photoUrl(String val) {
            photoUrl = val;
            return this;
        }

        public Builder owner(String val) {
            owner = val;
            return this;
        }

        public Builder ownerPhotoUrl(String val) {
            ownerPhotoUrl = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder height(Integer val) {
            if (val != null) height = val;
            else height = 0;
            return this;
        }

        public Builder height(String val) {
            if (val != null) height = Integer.parseInt(val);
            else height = 0;
            return this;
        }

        public Builder width(Integer val) {
            if (val != null) width = val;
            else width = 0;
            return this;
        }

        public Builder width(String val) {
            if (val != null) width = Integer.parseInt(val);
            else width = 0;
            return this;
        }

        public Builder photoSource(Integer val) {
            photoSource = val;
            return this;
        }

        public Photo build() {
            return new Photo(this);
        }
    }

    public static ArrayList<Photo> populatePhotoListFromCursor(Cursor photoCursor) {
        photoCursor.moveToFirst();
        ArrayList<Photo> photoList = new ArrayList<Photo>();

        while (photoCursor.moveToNext()) {
            photoList.add(new Photo.Builder()
                    .photoId(photoCursor.getString(photoCursor.getColumnIndex(DBConstants.KEY_PHOTO_ID)))
                    .photoUrl(photoCursor.getString(photoCursor.getColumnIndex(DBConstants.KEY_PHOTO_URL)))
                    .owner(photoCursor.getString(photoCursor.getColumnIndex(DBConstants.KEY_OWNER)))
                    .ownerPhotoUrl(photoCursor.getString(photoCursor.getColumnIndex(DBConstants.KEY_OWNER_PHOTO_URL)))
                    .title(photoCursor.getString(photoCursor.getColumnIndex(DBConstants.KEY_TITLE)))
                    .height(photoCursor.getInt(photoCursor.getColumnIndex(DBConstants.KEY_PHOTO_HEIGHT)))
                    .width(photoCursor.getInt(photoCursor.getColumnIndex(DBConstants.KEY_PHOTO_WIDTH)))
                    .photoSource(photoCursor.getInt(photoCursor.getColumnIndex(DBConstants.KEY_PHOTO_SOURCE)))
                    .build());
        }

        return photoList;
    }
}