public class Operations {

    public double sin(double x) {
        return Math.sin(x);
    }
 
    public double cos(double x) {
        return Math.cos(x);
    }

    public double tan(double x) {
        return Math.tan(x);
    }

    public  double log(double x){
        if (x<=0){
            throw new IllegalArgumentException("Lograrithm undefined for non-positive values");
        }else {
            return Math.log(x);
        }
    }

    public double sqrt(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Square root undefined for negative values");
        } else {
            return Math.sqrt(x);
        }
    }

    public double pow(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public double exp(double x) {
        return Math.exp(x);
    }

    public double factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial undefined for negative values");
        }
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public double absolute(double x) {
        return Math.abs(x);
    }

    public double radiansToDegrees(double radians) {
        return Math.toDegrees(radians);
    }

    public double degreesToRadians(double degrees) {
        return Math.toRadians(degrees);
    }


    public double hyperbolicSin(double x) {
        return Math.sinh(x);
    }


    public double hyperbolicCos(double x) {
        return Math.cosh(x);
    }

    public double hyperbolicTan(double x) {
        return Math.tanh(x);
    }

    public double hyperbolicCotangent(double x) {
        if (Math.sinh(x) == 0) {
            throw new IllegalArgumentException("Hyperbolic cotangent undefined for sinh(x) = 0");
        }
        return Math.cosh(x) / Math.sinh(x);
    }

    public double hyperbolicSecant(double x) {
        if (Math.cosh(x) == 0) {
            throw new IllegalArgumentException("Hyperbolic secant undefined for cosh(x) = 0");
        }
        return 1 / Math.cosh(x);
    }

    public double hyperbolicCosecant(double x) {
        if (Math.sinh(x) == 0) {
            throw new IllegalArgumentException("Hyperbolic cosecant undefined for sinh(x) = 0");
        }
        return 1 / Math.sinh(x);
    }

    public double logarithmBase10(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Logarithm base 10 undefined for non-positive values");
        }
        return Math.log10(x);
    }

    public double logarithmBase2(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Logarithm base 2 undefined for non-positive values");
        }
        return Math.log(x) / Math.log(2);
    }


    public double logarithmBaseN(double x, double base) {
        if (x <= 0 || base <= 0 || base == 1) {
            throw new IllegalArgumentException("Logarithm undefined for non-positive values or base equal to 1");
        }
        return Math.log(x) / Math.log(base);
    }


    public double cubeRoot(double x) {
        return Math.cbrt(x);
    }

    public double nthRoot(double x, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Root degree must be positive");
        }
        if (x < 0 && n % 2 == 0) {
            throw new IllegalArgumentException("Even root of negative number is undefined");
        }
        return Math.pow(x, 1.0 / n);
    }


    public double secant(double x) {
        if (Math.cos(x) == 0) {
            throw new IllegalArgumentException("Secant undefined for cos(x) = 0");
        }
        return 1 / Math.cos(x);
    }

    public double cosecant(double x) {
        if (Math.sin(x) == 0) {
            throw new IllegalArgumentException("Cosecant undefined for sin(x) = 0");
        }
        return 1 / Math.sin(x);
    }

    public double cotangent(double x) {
        if (Math.tan(x) == 0) {
            throw new IllegalArgumentException("Cotangent undefined for tan(x) = 0");
        }
        return 1 / Math.tan(x);
    }

    public double arcSin(double x) {
        if (x < -1 || x > 1) {
            throw new IllegalArgumentException("Arc sine undefined for values outside [-1, 1]");
        }
        return Math.asin(x);
    }

    public double arcCos(double x) {
        if (x < -1 || x > 1) {
            throw new IllegalArgumentException("Arc cosine undefined for values outside [-1, 1]");
        }
        return Math.acos(x);
    }

    public double arcTan(double x) {
        return Math.atan(x);
    }

    public double  archSec(double x) {
        if (x < 1 && x > -1) {
            throw new IllegalArgumentException("Arc secant undefined for |x| < 1");
        }else if (x == 1 || x == -1) {
            return 0;
        }else
        {
            return Math.acos(1 / x);
        }
    }

    public double archCosec(double x) {
        if (x < -1 || x > 1) {
            throw new IllegalArgumentException("Arc cosecant undefined for |x| < 1");
        } else if (x == 1 || x == -1) {
            return 0;
        } else {
            return Math.asin(1 / x);
        }
    }

    public double archCot(double x) {
        if (x == 0) {
            throw new IllegalArgumentException("Arc cotangent undefined for x = 0");
        }
        return Math.atan(1 / x);
    }
    
    public double archSinH(double x) {
        return Math.log(x + Math.sqrt(x * x + 1));
    }

    public double archCosH(double x) {
        if (x < 1) {
            throw new IllegalArgumentException("Arc hyperbolic cosine undefined for |x| < 1");
        }
        return Math.log(x + Math.sqrt(x * x - 1));
    }

    public double archTanH(double x) {
        if (x <= -1 || x >= 1) {
            throw new IllegalArgumentException("Arc hyperbolic tangent undefined for |x| >= 1");
        }
        return Math.log((1 + x) / (1 - x)) / 2;
    }


    public double archSecH(double x) {
        if (x < 1) {
            throw new IllegalArgumentException("Arc hyperbolic secant undefined for |x| < 1");
        }
        return Math.log(x + Math.sqrt(x * x - 1));
    }


    public double archCosecH(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Arc hyperbolic cosecant undefined for non-positive values");
        }
        return Math.log(x + Math.sqrt(x * x + 1));
    }


    public double archCotH(double x) {
        if (x == 0) {
            throw new IllegalArgumentException("Arc hyperbolic cotangent undefined for x = 0");
        }
        return Math.log((x + 1) / (x - 1)) / 2;
    }

    public double logNatural(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Natural logarithm undefined for non-positive values");
        }
        return Math.log(x);
    }


   public  double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is undefined");
        }
        return a / b;
    }
    public double modulus(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Modulus by zero is undefined");
        }
        return a % b;
    }

    
}
