package isac.galvao.validator;

import isac.galvao.validator.annotations.array.*;
import isac.galvao.validator.annotations.common.*;
import isac.galvao.validator.annotations.date.*;
import isac.galvao.validator.annotations.string.*;
import isac.galvao.validator.annotationvalidators.array.*;
import isac.galvao.validator.annotationvalidators.common.*;
import isac.galvao.validator.annotationvalidators.date.*;
import isac.galvao.validator.annotationvalidators.string.*;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class AnnotationValidatorBuilder {
    public static Map<Class<? extends Annotation>, AnnotationValidatorInterface<?>> build() {
        HashMap<Class<? extends Annotation>, AnnotationValidatorInterface<?>> validators = new HashMap<>();

        validators.put(Length.class, new LengthValidator());

        // Arrays
        validators.put(ArrayMaxSize.class, new ArrayMaxSizeValidator());
        validators.put(ArrayMinSize.class, new ArrayMinSizeValidator());
        validators.put(ArrayNotEmpty.class, new ArrayNotEmptyValidator());
        validators.put(ArrayUnique.class, new ArrayUniqueValidator());

        // Common
        validators.put(IsIn.class, new IsInValidator());
        validators.put(IsNotIn.class, new IsNotInValidator());
        validators.put(IsEmpty.class, new IsEmptyValidator());
        validators.put(IsNotEmpty.class, new IsNotEmptyValidator());
        validators.put(IsLatLong.class, new IsLatLongValidator());
        validators.put(IsLatLongDMS.class, new IsLatLongDMSValidator());
        validators.put(IsLatitude.class, new IsLatitudeValidator());
        validators.put(IsLongitude.class, new IsLongitudeValidator());
        validators.put(IsNull.class, new IsNullValidator());
        validators.put(IsNotNull.class, new IsNotNullValidator());
        validators.put(IsOptional.class, null);

        //Date
        validators.put(MaxDate.class, new MaxDateValidator());
        validators.put(MinDate.class, new MinDateValidator());

        //String
        validators.put(Contains.class, new ContainsValidator());
        validators.put(IsAlpha.class, new IsAlphaValidator());
        validators.put(IsAlphanumeric.class, new IsAlphanumericValidator());
        validators.put(IsAscii.class, new IsAsciiValidator());
        validators.put(IsBase32.class, new IsBase32Validator());
        validators.put(IsBase58.class, new IsBase58Validator());
        validators.put(IsBase64.class, new IsBase64Validator());
        validators.put(IsBIC.class, new IsBICValidator());
        validators.put(IsBooleanString.class, new IsBooleanStringValidator());
        validators.put(IsBtcAddress.class, new IsBtcAddressValidator());
        validators.put(IsByteLength.class, new IsByteLengthValidator());
        validators.put(IsCreditCard.class, new IsCreditCardValidator());
        validators.put(IsDataURI.class, new IsDataURIValidator());
        validators.put(IsDateString.class, new IsDateStringValidator());
        validators.put(IsISO31661Alpha2.class, new IsISO31661Alpha2Validator());
        validators.put(IsISO4217Currency.class, new IsISO4217CurrencyValidator());
        validators.put(IsDecimal.class, new IsDecimalValidator());
        validators.put(IsEAN.class, new IsEANValidator());
        validators.put(IsEmail.class, new IsEmailValidator());
        validators.put(IsEthereumAddress.class, new IsEthereumAddressValidator());
        validators.put(IsFirebasePushId.class, new IsFirebasePushIdValidator());
        validators.put(IsFQDN.class, new IsFQDNValidator());
        validators.put(IsFullWidth.class, new IsFullWidthValidator());
        validators.put(IsHalfWidth.class, new IsHalfWidthValidator());
        validators.put(IsHash.class, new IsHashValidator());
        validators.put(IsHexadecimal.class, new IsHexadecimalValidator());
        validators.put(IsHexColor.class, new IsHexColorValidator());
        validators.put(IsHSL.class, new IsHSLValidator());
        validators.put(IsIBAN.class, new IsIBANValidator());
        validators.put(IsIP.class, new IsIPValidator());
        validators.put(IsISBN.class, new IsISBNValidator());
        validators.put(IsISIN.class, new IsISINValidator());
        validators.put(IsISO31661Alpha3.class, new IsISO31661Alpha3Validator());
        validators.put(IsISRC.class, new IsISRCValidator());
        validators.put(IsISSN.class, new IsISSNValidator());
        validators.put(IsJWT.class, new IsJWTValidator());
        validators.put(IsLocale.class, new IsLocaleValidator());

        return validators;
    }
}
