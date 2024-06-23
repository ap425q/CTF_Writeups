            subl // SPDX-License-Identifier: UNLICENSED

pragma solidity = 0.4.25;

contract Sieze {
    bool public locked;
    string private flag;
    string public res;

    constructor(string memory _flag) public payable {
        locked = true;
        flag = _flag;
        res = "none";
    }
    
    function unlock() public payable {
        require(msg.value == 0.005 ether);
        locked = false;
    }
    
    function withdraw() public payable returns (bytes) {
        require(!locked, "Contract is locked");
        msg.sender.call.value(address(this).balance)();
        locked = true;
        res = flag;
        return bytes(flag);
    }
}
