package developer.exam.live.vi.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public class ExitDialogFragment extends DialogFragment {
    public ExitDialogFragment() {
    }

    public interface ExitDialogListener {
         void onDialogPositiveClick(DialogFragment dialog);
         void onDialogNegativeClick(DialogFragment dialog);
    }

    private ExitDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ExitDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Exit")
                .setMessage("Are you sure you want to exit");
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogPositiveClick(ExitDialogFragment.this);
                    }
                });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogNegativeClick(ExitDialogFragment.this);

                    }
                });
        return builder.create();
    }
}
