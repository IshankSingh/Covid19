package developer.exam.live.vi.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import developer.exam.live.vi.R;
import developer.exam.live.vi.utils.HomeDataItem;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.CoronaVirusHolder> implements Filterable {
    private Context mContext;
    private ArrayList<HomeDataItem> homeList;
    private ArrayList<HomeDataItem> mHomeListFilter;
    private boolean isFiltered = false;

    public HomeAdapter(Context mContext, ArrayList<HomeDataItem> homeList) {
        this.mContext = mContext;
        this.homeList = homeList;
        mHomeListFilter = new ArrayList<>(homeList);
    }

    @NonNull
    @Override
    public CoronaVirusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_data_list_item,parent,false);
        return new CoronaVirusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CoronaVirusHolder holder, final int position) {

        // Set country name
        holder.country_name.setText(homeList.get(position).getCountry_name());

        // Setting total cases
        String total_cases = homeList.get(position).getCountry_total_cases();
        String all_cases = "Total Cases: " + total_cases;
        holder.country_total_cases.setText(all_cases);
        total_cases = total_cases.replace(",","");

        // Setting Recovered Cases
        String total_recovered = homeList.get(position).getCountry_recovered_cases();
        String recovered_cases = "Recovered Cases: " + total_recovered;
        holder.country_recovered_cases.setText(recovered_cases);
        total_recovered = total_recovered.replace(",","");

        // Setting Death Cases
        String total_deaths = homeList.get(position).getCountry_death_cases();
        String death_cases = "Death Cases: " + total_deaths;
        holder.country_death_cases.setText(death_cases);
        total_deaths = total_deaths.replace(",","");

        String new_cases = homeList.get(position).getCountry_new_cases();
        new_cases = "New Cases: " + new_cases;
        holder.country_new_cases.setText(new_cases);

        // Setting pie chart
        float[] population = new float[3];
        population[0] = Integer.parseInt(total_cases);
        population[1] = Integer.parseInt(total_recovered);
        population[2] = Integer.parseInt(total_deaths);

        int active_cases =(int) (population[0] - population[1] - population[2]);
        String activeCases = "Active Cases: " + active_cases;
        holder.country_active_cases.setText(activeCases);

        // Menu Item Operation Begins

        holder.popUpMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mContext, holder.popUpMenu);
                popupMenu.inflate(R.menu.popup);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.home_country_covid19_data_share) {
                            String country;
                            if (position == 0) {
                                country = homeList.get(position).getCountry_name();
                            }else {
                                country = "Country: " + homeList.get(position).getCountry_name();
                            }
                            String message = country + "\n" +
                                    "Total Cases: " +homeList.get(position).getCountry_total_cases() +"\n" +
                                    "Total Recovered: " + homeList.get(position).getCountry_recovered_cases() + "\n" +
                                    "Total Deaths: " + homeList.get(position).getCountry_death_cases() + "\n" +
                                    "New cases added today: " + homeList.get(position).getCountry_new_cases();
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, message);
                            sendIntent.setType("text/plain");
                            mContext.startActivity(Intent.createChooser(sendIntent, "share with"));
                            return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        holder.addDataItems(population);

        // Country rank operation
        holder.country_rank_card_view.setVisibility(View.GONE);

//        if (!isFiltered) {
//            if (position == 0) {
//                holder.country_rank_card_view.setVisibility(View.GONE);
//            }else {
//                holder.country_rank_card_view.setVisibility(View.VISIBLE);
//                holder.country_rank.setText(String.valueOf(position));
//            }
//        }else {
//            holder.country_rank_card_view.setVisibility(View.GONE);
//        }


    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

    @Override
    public Filter getFilter() {
        return homeFilter;
    }

    private Filter homeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String key = constraint.toString().toLowerCase().trim();
            ArrayList<HomeDataItem> filteredList = new ArrayList<>();

            if (key.isEmpty()) {
                filteredList.addAll(mHomeListFilter);
                isFiltered = false;
            }else {
                for (HomeDataItem item : mHomeListFilter) {
                    if (item.getCountry_name().toLowerCase().contains(key)) {
                        filteredList.add(item);
                        isFiltered = true;
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            homeList.clear();
            homeList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    class CoronaVirusHolder extends RecyclerView.ViewHolder {
        TextView country_name, country_total_cases, country_recovered_cases, country_death_cases, country_new_cases, country_active_cases, country_rank;
        PieChart mPieChart;
        CardView country_rank_card_view;
        ImageView popUpMenu;

         CoronaVirusHolder(@NonNull View itemView) {
            super(itemView);

            //Binding text views and pie chart with their id's
            country_name = itemView.findViewById(R.id.home_country);
            country_total_cases = itemView.findViewById(R.id.home_country_total_cases);
            country_recovered_cases = itemView.findViewById(R.id.home_country_recovered_cases);
            country_death_cases = itemView.findViewById(R.id.home_country_death_cases);
            country_new_cases = itemView.findViewById(R.id.home_country_new_cases);
            country_active_cases = itemView.findViewById(R.id.home_country_active_cases);
            country_rank = itemView.findViewById(R.id.home_rank_TV);
            country_rank_card_view = itemView.findViewById(R.id.rank_view_group);
            mPieChart = itemView.findViewById(R.id.home_pie_chart);
            popUpMenu = itemView.findViewById(R.id.home_popup_menu_image);

            // performing different function in pie chart
            mPieChart.setRotationEnabled(true);
            mPieChart.getDescription().setText("Data in percentage(%)");
            mPieChart.getDescription().setTextSize(10);
            mPieChart.animateY(1200, Easing.EaseInOutCubic);
        }

        // Populating pie chart with data items
         void addDataItems(float[] population) {
            List<PieEntry> entries = new ArrayList<>();
            float total = population[0];
            population[0]=((population[0]-population[1]-population[2]) / total) * 100;
            population[1] = (population[1] / total) * 100;
            population[2] = (population[2] / total) * 100;

            for(float a : population)
            {
                entries.add(new PieEntry(a,""));
            }

            PieDataSet set = new PieDataSet(entries, "");
            PieData data = new PieData(set);
            data.setValueFormatter(new PercentFormatter());

            set.setSelectionShift(12);
            set.setValueTextSize(14);

            int[] colors = {rgb("#EF5350"), rgb("#66BB6A"), rgb("#FFC107")};

            set.setValueTextColor(Color.parseColor("#ffffff"));
            set.setColors(colors);
            setLegend(); // calling the set legend method
            mPieChart.setData(data);
            mPieChart.invalidate();

        }

        /*
        Setting the legend (setting up the labels or indicators of the pie chart)
        */
        private void setLegend() {
            Legend l = mPieChart.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
            l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            l.setDrawInside(false);
            List<LegendEntry> entryList = new ArrayList<>();
            LegendEntry l1 = new LegendEntry("Active Cases", Legend.LegendForm.SQUARE, 10f, 2f,
                    null, Color.parseColor("#ef5350"));
            LegendEntry l2 = new LegendEntry("Recovered Cases", Legend.LegendForm.SQUARE, 10f, 2f,
                    null, Color.parseColor("#66bb6a"));
            LegendEntry l3 = new LegendEntry("Death Cases", Legend.LegendForm.SQUARE, 10f, 2f,
                    null, Color.parseColor("#FFC107"));
            entryList.add(l1);
            entryList.add(l2);
            entryList.add(l3);
            l.setCustom(entryList);

        }
    }
}
