package id.alinea.dms.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.google.common.net.MediaType;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.alinea.dms.entity.SystemFile;
import id.alinea.dms.exception.StorageFileNotFoundException;
import id.alinea.dms.service.IStorageService;
import id.alinea.dms.service.ISystemFileService;
import id.alinea.dms.service.impl.DbSystemFileService;
import id.alinea.dms.service.impl.FileSystemStorageService;
import id.alinea.dms.utils.MimeTypes;

@Controller
public class FileUploadController {

	private Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private final IStorageService storageService;

	@Autowired
	private final ISystemFileService systemFileService;

	@Autowired
	public FileUploadController(FileSystemStorageService storageService, DbSystemFileService systemFileService) {
		this.storageService = storageService;
		this.systemFileService = systemFileService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {

		Iterable<SystemFile> sysFiles = systemFileService.getRepositoryLocator().getSystemFileRepository().findAll();
		List<SystemFile> files = Lists.newArrayList(sysFiles);

		model.addAttribute("files",
				files.stream()
						.map(file -> MvcUriComponentsBuilder
								.fromMethodName(FileUploadController.class, "serveFile",
										file.getFileUuid().concat(".")
												.concat(MimeTypes.getDefaultExt(file.getFileType())))
								.build().toUri().toString())
						.collect(Collectors.toList()));

		return "uploadForm";
	}

	@CrossOrigin
	@DeleteMapping("/{uuid}")
	public @ResponseBody String deleteFile(@PathVariable(value = "uuid") String uuid) throws IOException {
		systemFileService.deleteFileByUuid(uuid);
		return "OK";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("/view/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> viewFile(@PathVariable String filename) {
		if (StringUtils.isNotBlank(filename)) {
			final String uuid = FilenameUtils.removeExtension(filename);
			SystemFile sysFile = systemFileService.findSystemFileByUuid(uuid);
			if (sysFile != null) {
				Resource file = storageService.loadAsResource(filename);
				return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_TYPE, sysFile.getFileType())
					.body(file);				
			}
		}
		return ResponseEntity.notFound().build();
	}

	@CrossOrigin
	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "id", required = false) String entityId,
			@RequestParam(value = "name", required = false) String entityName,
			@RequestParam(value = "field", required = false) String entityField,
			RedirectAttributes redirectAttributes) {

		if (entityId != null && StringUtils.isNotBlank(entityName) && StringUtils.isNotBlank(entityField)) {
			systemFileService.storeFile(file, entityId, entityName, entityField);
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded " + file.getOriginalFilename() + "!");
		} else {
			redirectAttributes.addFlashAttribute("message", "Unable to upload " + file.getOriginalFilename() + "!");
		}

		return "redirect:/";
	}

	@CrossOrigin
	@PostMapping("/upload")
	public @ResponseBody String handleFileUploadAPI(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "id", required = false) String entityId,
			@RequestParam(value = "name", required = false) String entityName,
			@RequestParam(value = "field", required = false) String entityField) {

		String msg = null;
		if (entityId != null && StringUtils.isNotBlank(entityName) && StringUtils.isNotBlank(entityField)) {
			systemFileService.storeFile(file, entityId, entityName, entityField);
			msg = "You successfully uploaded " + file.getOriginalFilename() + "!";
		} else {
			msg = "Unable to upload " + file.getOriginalFilename() + "!";
		}

		logger.info("uploaded via API : {} ", msg);
		return msg;
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<Object> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}
