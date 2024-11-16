package com.example.appchat.mapper;

import com.example.appchat.dto.request.ImageRequest;
import com.example.appchat.dto.response.ImageResponse;
import com.example.appchat.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    Image toImage(ImageRequest request);

    ImageResponse toImageResponse(Image image);

    void updateImage(@MappingTarget Image image, ImageRequest request);

    List<ImageResponse> toListImageResponse(List<Image> imageList);
}
