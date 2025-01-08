
    import java.util.ArrayList;
    import java.util.Comparator;
    import java.util.HashMap;
import java.util.List;

    public class ParcelMap {
        private HashMap<String, Parcel> parcelMap;

        public ParcelMap() {
            this.parcelMap = new HashMap<>();
        }

        public void addParcel(Parcel parcel) {
            parcelMap.put(parcel.getId(), parcel);
        }

        public Parcel getParcel(String id) {
            return parcelMap.get(id);
        }

        public HashMap<String, Parcel> getAllParcels() {
            return parcelMap;
        }
        public void sortParcelsByWeight() {
    List<Parcel> parcelList = new ArrayList<>(parcelMap.values());
    parcelList.sort(Comparator.comparingDouble(Parcel::getWeight));
    parcelMap.clear();
    for (Parcel parcel : parcelList) {
        parcelMap.put(parcel.getId(), parcel);
    }
}

    }
    