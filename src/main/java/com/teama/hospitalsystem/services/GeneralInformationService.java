package com.teama.hospitalsystem.services;

import com.teama.hospitalsystem.models.GeneralInformation;

import java.math.BigInteger;

public interface GeneralInformationService {
    public void editGeneralInformation(GeneralInformation generalInformation);
    public GeneralInformation getGeneralInformation(BigInteger id);
    public GeneralInformation createGeneralInformation(GeneralInformation generalInformation);
}
