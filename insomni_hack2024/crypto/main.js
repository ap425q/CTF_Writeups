const readR1cs = require("r1csfile").readR1cs

readR1cs("flag_constraints.r1cs").then((r1cs) => {
	console.log(r1cs);
});