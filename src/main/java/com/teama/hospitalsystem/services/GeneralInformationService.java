package com.teama.hospitalsystem.services;

import com.teama.hospitalsystem.models.GeneralInformation;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

public interface GeneralInformationService {
    public void editGeneralInformation(GeneralInformation generalInformation);
    public GeneralInformation getGeneralInformation(BigInteger id);
    public void createGeneralInformation(GeneralInformation generalInformation);
}
