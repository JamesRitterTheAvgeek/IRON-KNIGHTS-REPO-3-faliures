package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name = "autoBlueBoard", group = "23274")
public class autoBlueBoard extends LinearOpMode {

    private DcMotor MotorFrontRight;
    private DcMotor MotorFrontLeft;
    private DcMotor MotorBackRight;
    private DcMotor MotorBackLeft;

    @Override
    public void runOpMode() throws InterruptedException {
        MotorFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        MotorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        MotorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        MotorBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(12, 70, Math.toRadians(-90));

        drive.setPoseEstimate(startPose);

        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)
                .forward(34)
                .waitSeconds(2)
                .back(10)
                .turn(Math.toRadians(90))
                .splineTo(new Vector2d(48,35),0)
                .build();

        waitForStart();

        if(!isStopRequested())
            drive.followTrajectorySequence(trajSeq);
    }
}
