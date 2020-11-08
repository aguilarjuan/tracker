package com.fiera.tracker.mapper;

import com.fiera.tracker.message.TrackerDTO;
import com.fiera.tracker.model.Tracker;
import org.springframework.stereotype.Component;

@Component
public class TrackerMapper {

    public Tracker toModel(String url, String link){
        Tracker trackerModel = new Tracker();
        trackerModel.setTarget(url);
        trackerModel.setLink(link);
        trackerModel.setValid(true);
        return trackerModel;
    }

    public TrackerDTO toDto(Tracker trackerModel){
     TrackerDTO trackerDTO = new TrackerDTO();
        trackerDTO.setLink(trackerModel.getLink());
        trackerDTO.setTarget(trackerModel.getTarget());
        trackerDTO.setValid(trackerModel.isValid());
        return trackerDTO;
    }
}
