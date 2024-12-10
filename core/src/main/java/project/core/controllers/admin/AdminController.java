package project.core.controllers.admin;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.core.services.admin.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PatchMapping("/set/role")
    public ResponseEntity<String> adminController(@RequestBody JsonNode jsonNode) {
        return ResponseEntity.ok(adminService.setRole(jsonNode));
    }

}
