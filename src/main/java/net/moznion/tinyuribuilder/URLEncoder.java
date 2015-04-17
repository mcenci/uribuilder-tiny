package net.moznion.tinyuribuilder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class URLEncoder {
  private final String encodingCharsetName;

  public URLEncoder(Charset encodingCharset) {
    this.encodingCharsetName = encodingCharset.name();
  }

  public String encode(String input) {
    try {
      return java.net.URLEncoder.encode(input, encodingCharsetName);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  public List<String> encode(List<String> input) {
    return input.stream()
        .map(item -> encode(item))
        .collect(Collectors.toList());
  }

  public Map<String, String> encode(Map<String, String> input) {
    return input.entrySet().stream()
        .collect(Collectors.toMap(
            kv -> encode(kv.getKey()),
            kv -> encode(kv.getValue())));
  }
}