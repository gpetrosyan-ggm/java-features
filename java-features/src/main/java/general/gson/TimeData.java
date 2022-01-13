package general.gson;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimeData {

    private String time;

    private Long milliseconds_since_epoch;

    private String date;

}
