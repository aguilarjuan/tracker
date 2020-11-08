package com.fiera.tracker.message;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @ToString
public class TrackerDTO {

private String target;
private String link;
private boolean valid;

}
