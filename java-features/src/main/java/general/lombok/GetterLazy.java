package general.lombok;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetterLazy {

    @Getter(lazy = true)
    private final Map<String, Long> transactions = getTransactions();

    private Map<String, Long> getTransactions() {

        final Map<String, Long> cache = new HashMap<>();
        List<String> txnRows = new ArrayList<>();// readTxnListFromFile();

        txnRows.forEach(s -> {
            String[] txnIdValueTuple = s.split("/");
            cache.put(txnIdValueTuple[0], Long.parseLong(txnIdValueTuple[1]));
        });

        return cache;
    }

}
