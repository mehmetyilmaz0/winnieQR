package com.mehmetyilmaz0.winnieqr;

import com.google.zxing.WriterException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Base64;

public class MainController {

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";

    @GetMapping("/qr")
    public String getQRCode(Model model) {
        String medium = "https://medium.com/@mehmetyilmaz0";
        String github = "https://github.com/mehmetyilmaz0";

        byte[] image = new byte[0];
        try {

            // Generate and Return Qr Code in Byte Array
            image = QRCodeGenerator.getQRCodeImage(medium, 500, 500);

            // Generate and Save Qr Code Image in static/image folder
            QRCodeGenerator.generateQRCodeImage(github, 500, 500, QR_CODE_IMAGE_PATH);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);

        model.addAttribute("medium", medium);
        model.addAttribute("github", github);
        model.addAttribute("qrcode", qrcode);

        return "qrcode";
    }
}
