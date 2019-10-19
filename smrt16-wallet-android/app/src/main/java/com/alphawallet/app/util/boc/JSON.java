package com.alphawallet.app.util.boc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Map;

import io.gsonfire.GsonFireBuilder;

public class JSON {
    private Gson gson;
    private JSON.DateTypeAdapter dateTypeAdapter = new JSON.DateTypeAdapter();
    private JSON.SqlDateTypeAdapter sqlDateTypeAdapter = new JSON.SqlDateTypeAdapter();
    private JSON.OffsetDateTimeTypeAdapter offsetDateTimeTypeAdapter = new JSON.OffsetDateTimeTypeAdapter();
    private JSON.LocalDateTypeAdapter localDateTypeAdapter = new JSON.LocalDateTypeAdapter();

    public static GsonBuilder createGson() {
        GsonFireBuilder fireBuilder = new GsonFireBuilder();
        return fireBuilder.createGsonBuilder();
    }

    private static String getDiscriminatorValue(JsonElement readElement, String discriminatorField) {
        JsonElement element = readElement.getAsJsonObject().get(discriminatorField);
        if (null == element) {
            throw new IllegalArgumentException("missing discriminator field: <" + discriminatorField + ">");
        } else {
            return element.getAsString();
        }
    }

    private static Class getClassByDiscriminator(Map classByDiscriminatorValue, String discriminatorValue) {
        Class clazz = (Class)classByDiscriminatorValue.get(discriminatorValue.toUpperCase());
        if (null == clazz) {
            throw new IllegalArgumentException("cannot determine model class of name: <" + discriminatorValue + ">");
        } else {
            return clazz;
        }
    }

    public JSON() {
        this.gson = createGson().registerTypeAdapter(Date.class, this.dateTypeAdapter).registerTypeAdapter(java.sql.Date.class, this.sqlDateTypeAdapter).registerTypeAdapter(OffsetDateTime.class, this.offsetDateTimeTypeAdapter).registerTypeAdapter(LocalDate.class, this.localDateTypeAdapter).create();
    }

    public Gson getGson() {
        return this.gson;
    }

    public JSON setGson(Gson gson) {
        this.gson = gson;
        return this;
    }

    public JSON setOffsetDateTimeFormat(DateTimeFormatter dateFormat) {
        this.offsetDateTimeTypeAdapter.setFormat(dateFormat);
        return this;
    }

    public JSON setLocalDateFormat(DateTimeFormatter dateFormat) {
        this.localDateTypeAdapter.setFormat(dateFormat);
        return this;
    }

    public JSON setDateFormat(DateFormat dateFormat) {
        this.dateTypeAdapter.setFormat(dateFormat);
        return this;
    }

    public JSON setSqlDateFormat(DateFormat dateFormat) {
        this.sqlDateTypeAdapter.setFormat(dateFormat);
        return this;
    }

    public static class DateTypeAdapter extends TypeAdapter<Date> {
        private DateFormat dateFormat;

        public DateTypeAdapter() {
        }

        public DateTypeAdapter(DateFormat dateFormat) {
            this.dateFormat = dateFormat;
        }

        public void setFormat(DateFormat dateFormat) {
            this.dateFormat = dateFormat;
        }

        public void write(JsonWriter out, Date date) throws IOException {
            if (date == null) {
                out.nullValue();
            } else {
                String value;
                if (this.dateFormat != null) {
                    value = this.dateFormat.format(date);
                } else {
                    value = ISO8601Utils.format(date, true);
                }

                out.value(value);
            }

        }

        public Date read(JsonReader in) throws IOException {
            try {
                switch(in.peek()) {
                    case NULL:
                        in.nextNull();
                        return null;
                    default:
                        String date = in.nextString();

                        try {
                            return this.dateFormat != null ? this.dateFormat.parse(date) : ISO8601Utils.parse(date, new ParsePosition(0));
                        } catch (ParseException var4) {
                            throw new JsonParseException(var4);
                        }
                }
            } catch (IllegalArgumentException var5) {
                throw new JsonParseException(var5);
            }
        }
    }

    public static class SqlDateTypeAdapter extends TypeAdapter<java.sql.Date> {
        private DateFormat dateFormat;

        public SqlDateTypeAdapter() {
        }

        public SqlDateTypeAdapter(DateFormat dateFormat) {
            this.dateFormat = dateFormat;
        }

        public void setFormat(DateFormat dateFormat) {
            this.dateFormat = dateFormat;
        }

        public void write(JsonWriter out, java.sql.Date date) throws IOException {
            if (date == null) {
                out.nullValue();
            } else {
                String value;
                if (this.dateFormat != null) {
                    value = this.dateFormat.format(date);
                } else {
                    value = date.toString();
                }

                out.value(value);
            }

        }

        public java.sql.Date read(JsonReader in) throws IOException {
            switch(in.peek()) {
                case NULL:
                    in.nextNull();
                    return null;
                default:
                    String date = in.nextString();

                    try {
                        return this.dateFormat != null ? new java.sql.Date(this.dateFormat.parse(date).getTime()) : new java.sql.Date(ISO8601Utils.parse(date, new ParsePosition(0)).getTime());
                    } catch (ParseException var4) {
                        throw new JsonParseException(var4);
                    }
            }
        }
    }

    public class LocalDateTypeAdapter extends TypeAdapter<LocalDate> {
        private DateTimeFormatter formatter;

        public LocalDateTypeAdapter() {
            this(DateTimeFormatter.ISO_LOCAL_DATE);
        }

        public LocalDateTypeAdapter(DateTimeFormatter formatter) {
            this.formatter = formatter;
        }

        public void setFormat(DateTimeFormatter dateFormat) {
            this.formatter = dateFormat;
        }

        public void write(JsonWriter out, LocalDate date) throws IOException {
            if (date == null) {
                out.nullValue();
            } else {
                out.value(this.formatter.format(date));
            }

        }

        public LocalDate read(JsonReader in) throws IOException {
            switch(in.peek()) {
                case NULL:
                    in.nextNull();
                    return null;
                default:
                    String date = in.nextString();
                    return LocalDate.parse(date, this.formatter);
            }
        }
    }

    public static class OffsetDateTimeTypeAdapter extends TypeAdapter<OffsetDateTime> {
        private DateTimeFormatter formatter;

        public OffsetDateTimeTypeAdapter() {
            this(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }

        public OffsetDateTimeTypeAdapter(DateTimeFormatter formatter) {
            this.formatter = formatter;
        }

        public void setFormat(DateTimeFormatter dateFormat) {
            this.formatter = dateFormat;
        }

        public void write(JsonWriter out, OffsetDateTime date) throws IOException {
            if (date == null) {
                out.nullValue();
            } else {
                out.value(this.formatter.format(date));
            }

        }

        public OffsetDateTime read(JsonReader in) throws IOException {
            switch(in.peek()) {
                case NULL:
                    in.nextNull();
                    return null;
                default:
                    String date = in.nextString();
                    if (date.endsWith("+0000")) {
                        date = date.substring(0, date.length() - 5) + "Z";
                    }

                    return OffsetDateTime.parse(date, this.formatter);
            }
        }
    }
}
