package flutter.plugins.contactsservice.contactsservice;

import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.HashMap;

import static android.provider.ContactsContract.CommonDataKinds;

/***
 * Represents an object which has a label and a value
 * such as an email or a phone
 ***/
class Item {

    String label, value;

    Item(String label, String value) {
        this.label = label;
        this.value = value;
    }

    HashMap<String, String> toMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("label", label);
        result.put("value", value);
        return result;
    }

    static Item fromMap(HashMap<String, String> map) {
        return new Item(map.get("label"), map.get("value"));
    }

    static String getPhoneLabel(int type) {
        switch (type) {
            case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                return "home";
            case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                return "work";
            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                return "mobile";
            default:
                return "other";
        }
    }

    static String getEmailLabel(int type, Cursor cursor) {
        switch (type) {
            case CommonDataKinds.Email.TYPE_HOME:
                return "home";
            case CommonDataKinds.Email.TYPE_WORK:
                return "work";
            case CommonDataKinds.Email.TYPE_MOBILE:
                return "mobile";
            case CommonDataKinds.Email.TYPE_CUSTOM:
                if (cursor.getString(cursor.getColumnIndex(CommonDataKinds.Email.LABEL)) != null) {
                    return cursor.getString(cursor.getColumnIndex(CommonDataKinds.Email.LABEL)).toLowerCase();
                } else return "";
            default:
                return "other";
        }
    }

    static int stringToPhoneType(String label) {
        if (label != null) {
            switch (label) {
                case "home":
                    return CommonDataKinds.Phone.TYPE_HOME;
                case "work":
                    return CommonDataKinds.Phone.TYPE_WORK;
                case "mobile":
                    return CommonDataKinds.Phone.TYPE_MOBILE;
                default:
                    return CommonDataKinds.Phone.TYPE_OTHER;
            }
        }
        return CommonDataKinds.Phone.TYPE_OTHER;
    }

    static int stringToEmailType(String label) {
        if (label != null) {
            switch (label) {
                case "home":
                    return CommonDataKinds.Email.TYPE_HOME;
                case "work":
                    return CommonDataKinds.Email.TYPE_WORK;
                case "mobile":
                    return CommonDataKinds.Email.TYPE_MOBILE;
                default:
                    return CommonDataKinds.Email.TYPE_OTHER;
            }
        }
        return CommonDataKinds.Email.TYPE_OTHER;
    }

}
