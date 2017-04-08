package com.github.alex.validate.web;

import com.github.alex.validate.model.User;
import com.github.alex.validate.model.UserRequest;
import com.github.alex.validate.util.HeaderUtil;
import com.github.alex.validate.util.IdWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.websocket.server.PathParam;

import io.swagger.annotations.ApiOperation;

/**
 * Created by ChenChang on 2017/4/8.
 */

@RestController
@RequestMapping(value = "/userResource")
public class UserResource {
    private final Logger log = LoggerFactory.getLogger(UserResource.class);
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());
    IdWorker idWorker = new IdWorker(2);

    @PostMapping
    @ApiOperation(value = "新增用户", notes = "新增用户")
    public ResponseEntity<User> createUser(@RequestBody UserRequest request) throws URISyntaxException {
        log.debug("REST request to create User: {}", request);
        User user = new User();
        BeanUtils.copyProperties(request, user);
        user.setId(idWorker.nextId());
        users.put(user.getId(), user);
        return ResponseEntity.created(new URI("/userResource/" + user.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(User.class.getSimpleName(), user.getId().toString()))
                .body(user);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取用户", notes = "获取用户")
    public ResponseEntity<User> getUser(@PathVariable  Long id) {
        log.debug("REST request to get User : {}", id);
        User user = users.get(id);
        return Optional.ofNullable(user)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    @ApiOperation(value = "更新用户", notes = "更新用户")
    public ResponseEntity<User> updateUser(@RequestBody UserRequest request)
            throws URISyntaxException {
        log.debug("REST request to update User : {}", request);
        if (request.getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        User user = users.get(request.getId());
        BeanUtils.copyProperties(request, user);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(User.class.getSimpleName(), user.getId().toString()))
                .body(user);
    }
}
