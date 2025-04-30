package com.dtb.event.controller;

@RestController
@RequestMapping("/events")
@Api(tags = "Event Service")
public class EventController {

    @PostMapping("/transaction")
    @ApiOperation(value = "Send a transaction event notification")
    public ResponseEntity<Void> sendTransactionEvent(@RequestBody TransactionEvent event) {
        // Logic to handle event
        return ResponseEntity.ok().build();
    }
}
