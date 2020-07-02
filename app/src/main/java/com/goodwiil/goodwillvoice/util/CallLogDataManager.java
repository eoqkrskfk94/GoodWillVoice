package com.goodwiil.goodwillvoice.util;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.ContactsContract;

import com.goodwiil.goodwillvoice.model.CallLogInfo;
import com.goodwiil.goodwillvoice.model.ContactInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.core.content.ContextCompat;

public class CallLogDataManager {

    //최근기록 불러오기
    public static ArrayList<CallLogInfo> getCallLog(Context context) {

        ArrayList<CallLogInfo> callLogInfos = new ArrayList<CallLogInfo>();

        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG);

        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null);
            int log_name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
            int log_number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int log_type = cursor.getColumnIndex(CallLog.Calls.TYPE);
            int log_date = cursor.getColumnIndex(CallLog.Calls.DATE);
            int log_duration = cursor.getColumnIndex(CallLog.Calls.DURATION);

            while (cursor.moveToNext()){
                CallLogInfo callLogInfo = new CallLogInfo();

                if(cursor.getString(log_name) == null)
                    callLogInfo.setName("unknown");
                else
                    callLogInfo.setName(cursor.getString(log_name));

                callLogInfo.setNumber(cursor.getString(log_number));
                callLogInfo.setDate(changeDate(cursor.getString(log_date)));
                callLogInfo.setDuration(cursor.getString(log_duration));

                String callType = cursor.getString(log_type);
                int code = Integer.parseInt(callType);
                switch(code){
                    case CallLog.Calls.OUTGOING_TYPE:
                        callLogInfo.setType("OUTGOING");
                        break;
                    case CallLog.Calls.INCOMING_TYPE:
                        callLogInfo.setType("INCOMING");
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        callLogInfo.setType("MISSED");
                        break;
                    case CallLog.Calls.REJECTED_TYPE:
                        callLogInfo.setType("REJECTED");
                        break;
                }

                callLogInfos.add(callLogInfo);


            }

            cursor.close();
        }

        else{
            ScreenManager.printToast(context, "최근기록 읽기 권한을 받아야 사용할수 있습니다.");
        }

        return callLogInfos;
    }

    //연락처 불러오기
    public static ArrayList<ContactInfo> getContacts(Context context){

        ArrayList<ContactInfo> contactInfos = new ArrayList<ContactInfo>();

        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS);

        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            Cursor cursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

            while (cursor.moveToNext()) {
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    ContactInfo contactsInfo = new ContactInfo();

                    String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    contactsInfo.setName(displayName);

                    Cursor phoneCursor = context.getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{contactId},
                            null);

                    if (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        contactsInfo.setPhoneNumber(phoneNumber.replaceAll("-", ""));
                    }

                    phoneCursor.close();

                    contactInfos.add(contactsInfo);
                }
            }
        }
        else{
            ScreenManager.printToast(context, "연락처 권한을 받아야 사용할수 있습니다.");
        }




        return contactInfos;
    }

    //날짜 형식 바꾸기 "yyyy/MM/dd HH:mm"
    public static String changeDate(String log_date){
        Date logDate = new Date(Long.valueOf(log_date));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String newDate = formatter.format(logDate);


        return newDate;
    }

    //전화번호 형식으로 바꾸기
    public static String convertNumber(String src) {
        if (src == null) {
            return "";
        }
        if (src.length() == 8) {
            return src.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
        } else if (src.length() == 12) {
            return src.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
        }
        return src.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
    }
}