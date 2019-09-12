package com.student.practiceexam.Database;

import android.provider.BaseColumns;

public final class UserMaster {
    public UserMaster() {
    }
    protected static class Users implements BaseColumns {

        public static final String TABLE_NAME = "User Table";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_PASSWORD = "Password";
        public static final String COLUMN_TYPE = "Type";

    }
    protected static class Messages implements BaseColumns{

        public static final String TABLE_NAME = "Message Table";
        public static final String COLUMN_USER = "User";
        public static final String COLUMN_SUBJECT = "Subject";
        public static final String COLUMN_MESSAGE = "Message";

    }
}
