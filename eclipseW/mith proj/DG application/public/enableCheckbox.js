function enableTwoG(chkPassport) {
        var twog = document.getElementById("2gid");
        twog.disabled = chkPassport.checked ? false : true;
        if (!twog.disabled) {
            twog.focus();
        }
    }
	function enableThreeG(chkPassport) {
        var threeg= document.getElementById("3gid");
        threeg.disabled = chkPassport.checked ? false : true;
        if (!threeg.disabled) {
            threeg.focus();
        }
    }
	function enableFourG(chkPassport) {
        var fourg= document.getElementById("4gid");
        fourg.disabled = chkPassport.checked ? false : true;
        if (!fourg.disabled) {
            fourg.focus();
        }
    }