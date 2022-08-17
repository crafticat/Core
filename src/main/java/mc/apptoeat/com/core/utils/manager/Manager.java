package mc.apptoeat.com.core.utils.manager;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Manager<V> {

    protected final List<V> list;

    public Manager() {
        list = new ArrayList<>();
    }

    public void register(V v) {
        list.add(v);
    }
}

