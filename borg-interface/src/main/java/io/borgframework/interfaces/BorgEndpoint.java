package io.borgframework.interfaces;

import java.io.Serializable;

public interface BorgEndpoint extends Serializable {
    BorgVersion getVersion();

    String getPathPrefix();

    void service(BorgHttpRequest request, BorgHttpResponse response);
}
