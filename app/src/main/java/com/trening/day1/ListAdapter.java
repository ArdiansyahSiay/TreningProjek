package com.trening.day1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private AppCompatActivity activity;
    private ArrayList<HashMap<String, String>> data;
    private int list_position = 0;
    private static LayoutInflater inflater = null;
    private String PACKAGE_NAME;


    public ListAdapter(AppCompatActivity a, ArrayList<HashMap<String, String>> d, int list_pos) {
        data = d;
        activity = a;
        list_position = list_pos;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        PACKAGE_NAME = activity.getPackageName();
    }

//    public ListAdapter(Context context,String[] imgUrl){
//        super(context,R.layout.lv_employee,imgUrl);
//        this.context = context;
//        this.imgUrl = imgUrl;
//
//        inflater = LayoutInflater.from(context);
//      }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get (position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
//        Picasso picasso = new Picasso.Builder(null).downloader(null).build();

        switch (list_position) {

            case 1:
                if (convertView == null)
                    vi = inflater.inflate(R.layout.lv_employee, null);

                    ImageView imgEmployee = vi.findViewById(R.id.img_employee);
                    TextView tvEmployeeName = vi.findViewById(R.id.tvEmployeeName);
                    TextView tvEmployeeNumber = vi.findViewById(R.id.tvEmpoleeNumber);
                    TextView tvEmployeeAddress = vi.findViewById(R.id.tvEmployeeAddress);
                    TextView tvEmployeeGender = vi.findViewById(R.id.tvEmployeeGender);

                    HashMap<String, String> empList = new HashMap<String, String>();

                    empList = data.get(position);

                    tvEmployeeName.setText(empList.get("employee_name"));
                    tvEmployeeNumber.setText(empList.get("nomor_induk_pegawai"));
                    tvEmployeeAddress.setText(empList.get("address"));
                    tvEmployeeGender.setText(empList.get("gender"));
                    Picasso.with(activity).load(empList.get("base_url")).resize(135,135).into(imgEmployee);

                    break;

                    default:
                        break;

        }
        return vi;
    }
}
