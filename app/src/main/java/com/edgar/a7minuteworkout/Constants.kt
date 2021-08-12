package com.edgar.a7minuteworkout

class Constants {
    companion object {
        fun defaultExerciseList(): ArrayList<ExerciseModel> {
            val exerciseList = ArrayList<ExerciseModel>()

            val squats = ExerciseModel(1, "Squats", R.drawable.ic_squats, false, false)
            exerciseList.add(squats)

            val dog = ExerciseModel(2, "Downward Dog", R.drawable.ic_dog, false, false)
            exerciseList.add(dog)

            val donkeyL = ExerciseModel(3, "Left Leg Donkey Kick", R.drawable.ic_donkey, false, false)
            exerciseList.add(donkeyL)

            val donkeyR = ExerciseModel(4, "Right Leg Donkey Kick", R.drawable.ic_donkey, false, false)
            exerciseList.add(donkeyR)

            val spiderman = ExerciseModel(5, "Spiderman", R.drawable.ic_spiderman, false, false)
            exerciseList.add(spiderman)

            val plank = ExerciseModel(6, "Plank", R.drawable.ic_plank, false, false)
            exerciseList.add(plank)

            val pushups = ExerciseModel(7, "Pushups", R.drawable.ic_pushups, false, false)
            exerciseList.add(pushups)

            val kickL = ExerciseModel(8, "Left Leg Vertical Kick", R.drawable.ic_vertical_kick, false, false)
            exerciseList.add(kickL)

            val kickR = ExerciseModel(9, "Right Leg Vertical Kick", R.drawable.ic_vertical_kick, false, false)
            exerciseList.add(kickR)

            val superman = ExerciseModel(10, "Superman", R.drawable.ic_superman, false, false)
            exerciseList.add(superman)

            val bridge = ExerciseModel(11, "Bridge", R.drawable.ic_bridge, false, false)
            exerciseList.add(bridge)

            val plank2 = ExerciseModel(12, "Plank", R.drawable.ic_plank, false, false)
            exerciseList.add(plank2)

            return exerciseList
        }
    }
}