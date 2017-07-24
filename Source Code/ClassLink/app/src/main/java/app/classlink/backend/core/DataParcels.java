package app.classlink.backend.core;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Class DataParcels : packages custom data types up to be passed through activities
 */
public class DataParcels implements Parcelable {
    private int mData;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mData);
    }

    /**
     * @Consructor Takes in a parcel and gives you an object populated with its values
     */
    protected DataParcels(Parcel in) {
        this.mData = in.readInt();
    }

    public static final Creator<DataParcels> CREATOR = new Creator<DataParcels>() {
        @Override
        public DataParcels createFromParcel(Parcel in) {
            return new DataParcels(in);
        }

        @Override
        public DataParcels[] newArray(int size) {
            return new DataParcels[size];
        }
    };
}
