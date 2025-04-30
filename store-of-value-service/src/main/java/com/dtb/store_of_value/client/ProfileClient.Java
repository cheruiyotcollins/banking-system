package com.dtb.store_of_value.client;

@FeignClient(name = "profile-service")
public interface ProfileClient {
    @GetMapping("/profiles/{id}")
    ProfileDto getProfileById(@PathVariable("id") Long id);
}