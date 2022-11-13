package com.br.api.goldenraspberryawards.util;

import java.util.List;

public interface FileReader<T> {

    List<T> read(String path);

}
