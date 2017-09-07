package com.android.alovia.projectqrcode;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alovia on 7/9/2560.
 */

public class EditRequest extends StringRequest {

    private static final String EDIT_REQUEST_URL = "http://jonhslim.pe.hu/pretest/Edit.php";
    private Map<String, String> params;

    public EditRequest(String enumber, String edata, String elocate, String edetail, String estatus, Response.Listener<String> Listener){
        super(Request.Method.POST, EDIT_REQUEST_URL, Listener, null);
        params = new HashMap<>();
        params.put("number", enumber);
        params.put("data", edata);
        params.put("locate", elocate);
        params.put("detail", edetail);
        params.put("status", estatus);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }

}


