package com.example.appchat.mapper;

import com.example.appchat.dto.request.FileRequest;
import com.example.appchat.dto.response.FileResponse;
import com.example.appchat.entity.File;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileMapper {
    File toFile(FileRequest request);

    FileResponse toFileResponse(File file);

    void updateFile(@MappingTarget File file, FileRequest request);

    List<FileResponse> toListFileResponse(List<File> fileList);
}
