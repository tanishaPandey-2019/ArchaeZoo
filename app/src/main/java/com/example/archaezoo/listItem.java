//package com.example.archaezoo;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.View;
//import android.widget.Adapter;
//import android.widget.AdapterView;
//import android.widget.EditText;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.RetryPolicy;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class listItem extends AppCompatActivity implements AdapterView.OnItemClickListener {
//
//
//    ListView listView;
//    SimpleAdapter adapter;
//    ProgressDialog loading;
//    EditText editTextSearch;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.list_item);
//
//        listView = (ListView) findViewById(R.id.lv_items);
//        listView.setOnItemClickListener(this);
//        editTextSearch = (EditText)findViewById(R.id.editTextSearch);
//
//        getItems();
//
//    }
//
//
//    private void getItems() {
//
//        loading =  ProgressDialog.show(this,"Loading","please wait",false,true);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbwyCVeRXtGUaowYZQdIak0UHJU_yvSv-idarQdr0gBzz2hhWavbwh5IXW0NC3_YiQJf/exec?action=getItems",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        parseItems(response);
//                    }
//                },
//
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }
//        );
//
//        int socketTimeOut = 50000;
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//
//        stringRequest.setRetryPolicy(policy);
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(stringRequest);
//
//    }
//
//
//    private void parseItems(String jsonResposnce) {
//
//        ArrayList<HashMap<String, String>> list = new ArrayList<>();
//
//        try {
//            JSONObject jobj = new JSONObject(jsonResposnce);
//            JSONArray jarray = jobj.getJSONArray("items");
//
//
//            for (int i = 0; i < jarray.length(); i++) {
//
//                JSONObject jo = jarray.getJSONObject(i);
//
//                String itemId = jo.getString("itemId");
//                String name = jo.getString("name");
//                String gender = jo.getString("gender");
//                String classStudent = jo.getString("classStudent");
//
//
//                HashMap<String, String> item = new HashMap<>();
//
//                item.put("itemId",itemId);
//                item.put("name",name );
//                item.put("gender", gender);
//                item.put("classStudent",classStudent);
//
//                list.add(item);
//
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        adapter = new SimpleAdapter(this,list,R.layout.list_item_row,
//                new String[]{"name","gender","classStudent","itemId"},new int[]{R.id.tv_item_name,R.id.tv_brand,R.id.tv_price});
//
//
//        listView.setAdapter(adapter);
//        loading.dismiss();
//
//        editTextSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                listItem.this.adapter.getFilter().filter(s);
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//    }
//
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//        Intent intent = new Intent(this, itemDetails.class);
//        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
//        String itemId = map.get("itemId").toString();
//        String name = map.get("name").toString();
//        String gender = map.get("gender").toString();
//        String classStudent = map.get("classStudent").toString();
//
//
//        // String sno = map.get("sno").toString();
//
//        // Log.e("SNO test",sno);
//        intent.putExtra("itemId",itemId);
//        intent.putExtra("name",name);
//        intent.putExtra("gender",gender);
//        intent.putExtra("classStudent",classStudent);
//
//
//        startActivity(intent);
//
//    }
//
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
//
//
//}
