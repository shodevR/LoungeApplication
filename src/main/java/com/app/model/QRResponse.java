package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QRResponse {
    private String uniqueCode;
    private String qrImage;
    private boolean flag;
    private String expire;
}
